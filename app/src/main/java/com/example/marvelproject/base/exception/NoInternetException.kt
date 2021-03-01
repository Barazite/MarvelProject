package com.example.marvelproject.base.exception

import java.io.IOException

class NoInternetException(message: String = "No internet connection available") : IOException(message)