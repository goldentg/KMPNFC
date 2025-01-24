# KMPNFC
A Kotlin Multiplatform library for NFC integration

## Usage Instructions

### Android

1. Initialize the `NFCManager` in your Activity:

```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var nfcManager: NFCManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nfcManager = NFCManager(this)
    }

    override fun onResume() {
        super.onResume()
        nfcManager.enableNFC()
    }

    override fun onPause() {
        super.onPause()
        nfcManager.disableNFC()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val nfcData = nfcManager.readNFC(intent)
        // Handle the NFC data
    }
}
```

### iOS

1. Initialize the `NFCManager` in your ViewController:

```swift
class ViewController: UIViewController {

    private var nfcManager: NFCManager?

    override func viewDidLoad() {
        super.viewDidLoad()
        nfcManager = NFCManager()
    }

    @IBAction func startNFCSession(_ sender: Any) {
        nfcManager?.enableNFC()
    }

    @IBAction func stopNFCSession(_ sender: Any) {
        nfcManager?.disableNFC()
    }
}
```
