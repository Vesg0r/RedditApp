package android.bignerdrach.redditapp.presentation.activities

import android.bignerdrach.redditapp.R
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class ImageActivity : AppCompatActivity() {
    lateinit var image: ImageView
    lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        val imageUrl = intent.getStringExtra("url")
        image = findViewById(R.id.image_fullscreen)
        saveButton = findViewById(R.id.save_button)
        Glide.with(image.context).load(imageUrl).into(image)
        saveButton.setOnClickListener {
            downloadImage(image)
        }
    }

    private fun downloadImage(image: ImageView) {
        val bitmap = (image.drawable as BitmapDrawable).bitmap
        val filename = "${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null

        //For devices running android >= Q
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            baseContext?.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            //These for devices running on android < Q
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val imageToDownload = File(imagesDir, filename)
            fos = FileOutputStream(imageToDownload)
        }

        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(baseContext, "Saved", Toast.LENGTH_SHORT)
                .show()
        }
    }
}