package com.example.factorialtestapp

sealed class State()

data object Error : State()
data object Progress : State()
class Factorial(val factorial: String) : State()