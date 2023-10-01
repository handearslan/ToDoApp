package com.handearslan.todoapphomework.data.model

import com.handearslan.todoapphomework.R

enum class Priority(val colorResId: Int) {
    LOW(R.color.lowPriorityColor),
    MEDIUM(R.color.mediumPriorityColor),
    HIGH(R.color.highPriorityColor);
}