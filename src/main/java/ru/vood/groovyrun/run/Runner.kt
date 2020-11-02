package ru.vood.groovyrun.run

import groovy.lang.GroovyClassLoader
import groovy.lang.GroovyShell
import groovy.util.GroovyScriptEngine
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service
import java.io.File
import java.util.*
import java.util.stream.IntStream

@Service
class Runner : CommandLineRunner {

    override fun run(vararg args: String) {
        runWithGroovyShell()
        println(Thread.currentThread())
        val time = Date().time
        IntStream.range(1,1000).parallel().forEach { runWithGroovyShellMap() }

        println(time-Date().time)
        //        runWithGroovyClassLoader() ;
//        runWithGroovyScriptEngine() ;
    }

    companion object {
        fun runWithGroovyShell() {
            GroovyShell().parse(File("test.groovy")).invokeMethod("hello_world", null)
        }

        fun runWithGroovyShellMap() {
            GroovyShell().parse(File("test.groovy")).invokeMethod("hello_world_map", mapOf("q" to 1))
        }

        fun runWithGroovyClassLoader() {
            // Declaring a class to conform to a java interface class would get rid of
            // a lot of the reflection here
            val scriptClass = GroovyClassLoader().parseClass(File("test.groovy"))
            val scriptInstance = scriptClass.newInstance()
            scriptClass.getDeclaredMethod("hello_world", *arrayOf()).invoke(scriptInstance, *arrayOf())
        }

        fun runWithGroovyScriptEngine() {
            // Declaring a class to conform to a java interface class would get rid of
            // a lot of the reflection here
            val scriptClass = GroovyScriptEngine(".").loadScriptByName("test.groovy")
            val scriptInstance = scriptClass.newInstance()
            scriptClass.getDeclaredMethod("hello_world", *arrayOf()).invoke(scriptInstance, *arrayOf())
        }
    }
}