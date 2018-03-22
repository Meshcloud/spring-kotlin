package de.meshcloud.example.springkotlin

import java.text.SimpleDateFormat
import java.util.*

val customFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")

fun Date.formatted(): String {
  return customFormat.format(this)
}