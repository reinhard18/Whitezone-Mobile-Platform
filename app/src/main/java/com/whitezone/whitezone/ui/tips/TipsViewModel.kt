package com.whitezone.whitezone.ui.tips

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TipsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "1. Jaga tangan Anda tetap bersih\n" +
                "Cuci tangan anda secara teratur setidaknya 40 detik dengan sabun dan air hangat atau gunakan antiseptik berbasis alkohol. Ini membantu membunuh kuman yang mungkin ada ditangan anda.\n\n" +
                "2. Hindari menyentuh mata, hidung, dan mulut Anda.\n" +
                "Tangan menyentuh banyak permukaan dan bisa terkena virus. Setelah terkontaminasi, tangan dapat memindahkan virus ke mata, hidung, atau mulut Anda. Dari sana, virus bisa masuk ke tubuh Anda dan menginfeksi Anda.\n\n" +
                "3. Tutupi mulut dan hidung Anda dengan siku atau tisu yang tertekuk saat batuk atau bersin.\n" +
                "Kemudian segera buang tisu bekas ke tempat sampah tertutup dan cuci tangan Anda. Dengan mengikuti 'kebersihan pernapasan' yang baik, Anda melindungi orang-orang di sekitar Anda dari virus yang menyebabkan pilek, flu, dan COVID-19.\n\n" +
                "4. Bersihkan dan disinfeksi permukaan lingkungan.\n" +
                "Bersihkan dan disinfeksi permukaan secara berkala terutama yang sering disentuh, seperti gagang pintu, keran, dan layar ponsel."
    }
    val text: LiveData<String> = _text
}