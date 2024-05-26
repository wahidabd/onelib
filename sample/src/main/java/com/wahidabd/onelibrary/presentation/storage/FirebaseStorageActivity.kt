package com.wahidabd.onelibrary.presentation.storage

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import androidx.activity.result.contract.ActivityResultContracts
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.onelibrary.databinding.ActivityFirebaseStorageBinding
import com.wahidabd.onelibrary.domain.firebase.storage.model.StorageParam
import com.wahidabd.onelibrary.utils.uriToFile
import org.koin.android.ext.android.inject
import java.io.File

class FirebaseStorageActivity : BaseActivity<ActivityFirebaseStorageBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, FirebaseStorageActivity::class.java))
        }
    }

    private var file: File? = null
    private val viewModel: FirebaseStorageViewModel by inject()

    private val requestFileLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val uri = data?.data
                file = uriToFile(this, uri!!)

                binding.tvFilePath.text = file?.path
            }
        }

    override fun getViewBinding(): ActivityFirebaseStorageBinding {
        return ActivityFirebaseStorageBinding.inflate(layoutInflater)
    }

    override fun initIntent() {}

    override fun initUI() {}

    override fun initAction() {
        binding.btnSelect.onClick {
            storagePermissionRequest.launch(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE))
        }

        binding.btnSubmit.onClick {

            if (file != null) {

                val name = file?.name.toString()
                val body = StorageParam(name, file!!)
                viewModel.addFile(body)
            } else {
                showToast("Please select a file")
            }

        }
    }

    override fun initProcess() {}

    override fun initObservers() {}

    private fun openSpecificFolder() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"  // Set the MIME type to filter files
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                putExtra(
                    DocumentsContract.EXTRA_INITIAL_URI,
                    Uri.parse("/storage/emulated/0/Download")
                )
            }
        }

        requestFileLauncher.launch(intent)
    }

    private val storagePermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.getValue("android.permission.READ_EXTERNAL_STORAGE")
            if (granted) {
                // Access the file here (permission granted)
                openSpecificFolder()
            } else {
                // Explain why permission is needed (optional)
                showToast("Storage permission is required to access files.")
            }
        }

}