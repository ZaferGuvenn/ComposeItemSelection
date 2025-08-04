# Android Multi-Selection UI Örneği

Modern Android uygulamalarında görülen çoklu seçim (multi-selection) arayüzünün Jetpack Compose ile implementasyonu.

## Özellikler

✔️ Uzun basarak seçim moduna geçme  
✔️ Çoklu öğe seçimi  
✔️ Context-aware AppBar (Seçim modunda otomatik değişim)  
✔️ Jetpack Compose ile modern UI  
✔️ Tamamen Kotlin ile yazılmış

## Nasıl Çalıştırılır?

1. Projeyi klonlayın:
```bash
git clone [git-url]
```

2. Android Studio'da açın:
    - `File > Open` ile proje dizinini seçin

3. Emülatör veya fiziksel cihazda çalıştırın:
    - ▶️ Run butonuna basın (Shift+F10)

## Teknoloji Yığını

- %100 Kotlin
- Jetpack Compose
- Material 3 Tasarım Sistemi

## Kullanım

```kotlin
// Öğe seçim mekanizması
var selectedItems by remember { mutableStateOf(setOf<Int>()) }

// Seçim modu kontrolü
val isSelectionMode = selectedItems.isNotEmpty()
```

## Lisans

MIT Lisansı - Detaylar için [LICENSE](LICENSE) dosyasına bakın.

---

✍️ **Not**: Bu proje öğrenme amaçlı hazırlanmıştır. Geliştirmeye açıktır.