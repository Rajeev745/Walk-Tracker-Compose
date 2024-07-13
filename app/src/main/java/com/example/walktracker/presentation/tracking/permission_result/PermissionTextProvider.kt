package com.example.walktracker.presentation.tracking.permission_result

interface PermissionTextProvider {

    fun getDescription(isPermanentlyDeclined: Boolean): String
}