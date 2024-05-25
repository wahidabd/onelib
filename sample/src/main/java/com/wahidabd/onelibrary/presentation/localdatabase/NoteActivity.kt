package com.wahidabd.onelibrary.presentation.localdatabase

import android.content.Context
import android.content.Intent
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.toStringTrim
import com.wahidabd.onelibrary.databinding.ActivityNoteBinding
import com.wahidabd.onelibrary.domain.note.model.Note
import com.wahidabd.onelibrary.presentation.localdatabase.adapter.NoteAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteActivity : BaseActivity<ActivityNoteBinding>() {

    private val viewModel: NoteViewModel by viewModel()
    private val adapter by lazy {
        NoteAdapter {

        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, NoteActivity::class.java))
        }
    }

    override fun getViewBinding(): ActivityNoteBinding =
        ActivityNoteBinding.inflate(layoutInflater)

    override fun initIntent() {}

    override fun initUI() {
        binding.rvNote.adapter = adapter
    }

    override fun initAction() {
        with(binding) {
            btnSave.onClick {
                val title = etTitle.toStringTrim()
                val desc = etDesc.toStringTrim()

                val data = Note(title = title, description = desc)
                viewModel.addNote(data)
                viewModel.getNotes()
            }
        }
    }

    override fun initProcess() {
        viewModel.getNotes()
    }

    override fun initObservers() {
        viewModel.getNotes.observe(this) {
            adapter.setData = it
        }
    }

}