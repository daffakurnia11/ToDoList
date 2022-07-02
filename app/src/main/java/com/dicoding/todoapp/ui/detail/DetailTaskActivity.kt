package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID

class DetailTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val viewModel = ViewModelFactory.getInstance(this)
        val detailViewModel: DetailTaskViewModel =
            ViewModelProvider(this, viewModel).get(DetailTaskViewModel::class.java)

        detailViewModel.setTaskId(intent.getIntExtra(TASK_ID, 0))
        detailViewModel.task.observe(this) {
            if (it != null) {
                val detailEditTitle: TextView = findViewById(R.id.detail_ed_title)
                detailEditTitle.text = it.title

                val detailEditDesc: TextView = findViewById(R.id.detail_ed_description)
                detailEditDesc.text = it.description

                val detailEditDueDate: TextView = findViewById(R.id.detail_ed_due_date)
                detailEditDueDate.text = DateConverter.convertMillisToString(it.dueDateMillis)

                val btnDeleteTask: Button = findViewById(R.id.btn_delete_task)
                btnDeleteTask.setOnClickListener {
                    detailViewModel.deleteTask()
                    Toast.makeText(
                        applicationContext,
                        "Your task has been deleted!",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }
        }
    }
}