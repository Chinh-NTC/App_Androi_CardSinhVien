package com.example.studentprofilecard

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentprofilecard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private fun bindStudentData(student: Student){
        binding.tvName.text = student.name
        binding.tvStudentId.text = "Mssv: ${student.id} | Lớp: ${student.className}"

        val rank = when{
            student.gpa >= 3.6 -> "Xuất sắc"
            student.gpa >= 3.2 -> "Giỏi"
            student.gpa >= 2.5 -> "Khá"
            else -> "Trung bình"
        }

        binding.tvGpa.text = "${student.gpa} GPA ($rank)"
    }

    private lateinit var binding: ActivityMainBinding

    private var currentStudent = Student(
        id = "2415053122301",
        name = "Nguyễn Tấn Chinh",
        className = "24T3",
        email = "chinh2511006@gmail.com",
        gpa = 3.6
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindStudentData(currentStudent)

        binding.btnUpdateGpa.setOnClickListener {
            val inputStr = binding.edtNewGpa.text.toString().trim()
            val newGpa = inputStr.toDoubleOrNull()

            if(newGpa == null || newGpa !in 0.0..4.0){
                binding.edtNewGpa.error = "Vui lòng nhập GPA hợp lệ (0.0 - 4.0)!"
                Toast.makeText(this, "Điểm GPA không hợp lệ!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            currentStudent = currentStudent.copy(gpa = newGpa)
            bindStudentData(currentStudent)

            Toast.makeText(this, "Cập nhật điểm thành công!", Toast.LENGTH_SHORT).show()
            binding.edtNewGpa.text.clear()
        }
    }
}