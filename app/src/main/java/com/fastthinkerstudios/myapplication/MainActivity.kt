package com.fastthinkerstudios.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme {
                SelectionScreen()
            }
        }
    }
}

@Composable
fun SelectionScreen() {
    var selectedItems by remember { mutableStateOf(setOf<Int>()) }
    val isSelectionMode = selectedItems.isNotEmpty()

    Scaffold(
        topBar = {
            if (isSelectionMode) {
                SelectionTopBar(
                    selectedCount = selectedItems.size,
                    onCancel = { selectedItems = emptySet() },
                    onEdit = { /* edit işlemleri */ },
                    onDelete = { /* delete işlemleri */ },
                    onShare = { /* share işlemleri */ }
                )
            } else {
                DefaultTopAppBar()
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(50) { index ->
                val selected = selectedItems.contains(index)

                SelectableListItem(
                    index = index,
                    selected = selected,
                    isSelectionMode = isSelectionMode,
                    selectedItems = selectedItems,
                    onItemClick = { /* Normal tıklama işlemi */ },
                    onSelectionChange = { newSelection -> selectedItems = newSelection }
                )
            }
        }
    }
}

@Composable
fun SelectableListItem(
    index: Int,
    selected: Boolean,
    isSelectionMode: Boolean,
    selectedItems: Set<Int>,
    onItemClick: () -> Unit,
    onSelectionChange: (Set<Int>) -> Unit
) {
    ListItem(
        headlineContent = { Text("Item $index") },
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (selected) Color.LightGray.copy(alpha = 0.3f)
                else Color.Transparent
            )
            .pointerInput(index, selected, isSelectionMode, selectedItems) {
                detectTapGestures(
                    onTap = {
                        if (isSelectionMode) {
                            onSelectionChange(
                                if (selected) selectedItems - index
                                else selectedItems + index
                            )
                        } else {
                            onItemClick()
                        }
                    },
                    onLongPress = {
                        onSelectionChange(
                            if (selected) selectedItems - index
                            else selectedItems + index
                        )
                    }
                )
            }
            .padding(16.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionTopBar(
    selectedCount: Int,
    onCancel: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onShare: () -> Unit
) {
    TopAppBar(
        title = { Text("$selectedCount selected") },
        navigationIcon = {
            IconButton(onClick = onCancel) {
                Icon(Icons.Default.Close, contentDescription = "Cancel")
            }
        },
        actions = {
            IconButton(onClick = onEdit) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
            IconButton(onClick = onShare) {
                Icon(Icons.Default.Share, contentDescription = "Share")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBar() {
    TopAppBar(
        title = { Text("Normal App Bar") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        content = content
    )
}