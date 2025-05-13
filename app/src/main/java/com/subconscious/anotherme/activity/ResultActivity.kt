package com.subconscious.anotherme.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.subconscious.anotherme.adapter.SelectedAppAdapter
import com.subconscious.anotherme.databinding.ActivityResultBinding
import com.subconscious.anotherme.util.ClipboardUtil
import com.subconscious.anotherme.util.JumpUtil
import com.subconscious.anotherme.util.ScreenUtil
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var adapter: SelectedAppAdapter

    private var mediaProjectionManager: MediaProjectionManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 设置 Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        setupRecyclerView()

        val selectedApps = intent.getStringArrayListExtra("selectedApps") ?: mutableListOf()
        adapter.data = selectedApps
        adapter.notifyDataSetChanged()

        showSystemSettingsPage(this)
        mediaProjectionManager =
            getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager?

        ScreenUtil.init(this)
        ClipboardUtil.init(this)
        JumpUtil.init(this)
    }

    private fun showSystemSettingsPage(mContext: Context?) {
        val mIntent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        mContext?.startActivity(mIntent)
    }

    private fun setupRecyclerView() {
        adapter = SelectedAppAdapter()
        binding.rvSelectedApps.apply {
            layoutManager = LinearLayoutManager(this@ResultActivity)
            adapter = this@ResultActivity.adapter
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        binding.etClipboardText.performClick()
    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 123123 && resultCode == RESULT_OK && data != null) {
//            // 获取屏幕尺寸
//            val metrics = resources.displayMetrics
//            val width = metrics.widthPixels
//            val height = metrics.heightPixels
//            val density = metrics.densityDpi
//
//            val mediaProjection = mediaProjectionManager?.getMediaProjection(resultCode, data)
//            val imageReader = ImageReader.newInstance(width, height, PixelFormat.RGBA_8888, 2)
//            val surface = imageReader.surface
//
//            val virtualDisplay = mediaProjection?.createVirtualDisplay(
//                "ScreenCapture",
//                width, height, density,
//                DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
//                surface, null, null
//            )
//
//            imageReader.setOnImageAvailableListener({ reader ->
//                val image = reader.acquireLatestImage()
//                // 处理截图
//                val planes = image.planes
//                val buffer = planes[0].buffer
//
//                val pixelStride = planes[0].pixelStride
//                val rowStride = planes[0].rowStride
//                val rowPadding = rowStride - pixelStride * width
//
//                val bitmap = Bitmap.createBitmap(
//                    width + rowPadding / pixelStride,
//                    height,
//                    Bitmap.Config.ARGB_8888
//                )
//                bitmap.copyPixelsFromBuffer(buffer)
//
//                // 保存截图
//                saveBitmap(bitmap)
//
//                image.close()
//            }, null)
//        }
//    }

    private fun saveBitmap(bitmap: Bitmap) {
        try {
            val fileName = "screenshot_${System.currentTimeMillis()}.png"
            val file = File(Environment.getExternalStorageDirectory(), fileName)

            FileOutputStream(file).use { fos ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
                fos.flush()
            }

            // 通知媒体库更新
            MediaScannerConnection.scanFile(
                this,
                arrayOf(file.toString()),
                null,
                null
            )

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
} 