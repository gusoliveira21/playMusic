package br.com.gusoliveira21.playmusic.util

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
//Nota: Criar uma função de permissão, para que seja possível reduzir o código
class Util {
    //Todo: Preciso fazer isso funcionar para retirar essa função dos fragmentos
    /*private fun hasPermission(ctx: Context): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            if ((ActivityCompat.checkSelfPermission(ctx,android.Manifest.permission.READ_EXTERNAL_STORAGE)) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 123)
                if ((ActivityCompat.checkSelfPermission(ctx, android.Manifest.permission.READ_EXTERNAL_STORAGE)) != PackageManager.PERMISSION_GRANTED) {
                    return hasPermission(ctx)
                }
            } else {
                return true
            }
        }
        return false
    }*/
}