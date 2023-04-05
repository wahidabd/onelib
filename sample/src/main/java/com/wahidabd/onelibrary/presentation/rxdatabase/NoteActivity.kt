package com.wahidabd.onelibrary.presentation.rxdatabase

import android.content.Context
import android.content.Intent
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.common.showToast
import com.wahidabd.library.utils.extensions.debug
import com.wahidabd.library.utils.exts.clear
import com.wahidabd.library.utils.exts.observerLiveData
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.toStringTrim
import com.wahidabd.onelibrary.databinding.ActivityNoteBinding
import com.wahidabd.onelibrary.domain.note.model.Note
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteActivity : BaseActivity<ActivityNoteBinding>() {

    private val viewModel: NoteViewModel by viewModel()

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, NoteActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityNoteBinding =
        ActivityNoteBinding.inflate(layoutInflater)

    override fun initUI() {}

    override fun initAction() {
        with(binding){
            btnSave.onClick {
                val title = etTitle.toStringTrim()
                val desc = etDesc.toStringTrim()

                val data = Note(title = title, description = desc)
                viewModel.addNote(data)
            }
        }
    }

    override fun initProcess() {}

    override fun initObservers() {
        viewModel.addNote.observerLiveData(this,
            onLoading = {},
            onEmpty = {},
            onFailure = {_, m ->
                showToast(m.toString())
            },
            onSuccess = {
                debug { "Success" }
                binding.etTitle.clear()
                binding.etDesc.clear()
            }
        )
    }

}