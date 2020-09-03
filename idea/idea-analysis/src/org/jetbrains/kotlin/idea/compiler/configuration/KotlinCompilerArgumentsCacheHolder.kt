/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.compiler.configuration

import com.intellij.openapi.components.*
import com.intellij.openapi.module.Module
import com.intellij.util.xmlb.Converter
import com.intellij.util.xmlb.XmlSerializerUtil
import com.intellij.util.xmlb.annotations.OptionTag
import org.jetbrains.kotlin.idea.util.application.getServiceSafe
import org.jetbrains.kotlin.utils.addToStdlib.runIf

typealias ClasspathArgumentCacheIndexType = List<Int>

const val CLASSPATH_ARGUMENT_CACHE_INDEXES_SEPARATOR = ";"

class ClasspathArgumentCacheIndexConverter : Converter<ClasspathArgumentCacheIndexType>() {
    override fun toString(value: ClasspathArgumentCacheIndexType): String? =
        runIf(value.isNotEmpty()) {
            value.joinToString(CLASSPATH_ARGUMENT_CACHE_INDEXES_SEPARATOR)
        }


    override fun fromString(value: String): ClasspathArgumentCacheIndexType? =
        value.split(CLASSPATH_ARGUMENT_CACHE_INDEXES_SEPARATOR).mapNotNull { it.toIntOrNull() }

}

@State(
    name = "CompilerArgumentsCacheIndexes",
    storages = [(Storage(file = StoragePathMacros.MODULE_FILE))]
)
class KotlinCompilerArgumentsCacheIndexesModuleComponent : PersistentStateComponent<KotlinCompilerArgumentsCacheIndexesModuleComponent> {
    var currentCommonArgumentsCacheIds: List<Int> = listOf()
    var defaultCommonArgumentsCacheIds: List<Int> = listOf()

    @OptionTag(converter = ClasspathArgumentCacheIndexConverter::class)
    var currentClasspathArgumentsCacheIds: List<ClasspathArgumentCacheIndexType> = listOf()

    @OptionTag(converter = ClasspathArgumentCacheIndexConverter::class)
    var defaultClasspathArgumentsCacheIds: List<ClasspathArgumentCacheIndexType> = listOf()

    @OptionTag(converter = ClasspathArgumentCacheIndexConverter::class)
    var dependencyClasspathCacheIds: List<ClasspathArgumentCacheIndexType> = listOf()

    override fun getState(): KotlinCompilerArgumentsCacheIndexesModuleComponent? = this

    override fun loadState(state: KotlinCompilerArgumentsCacheIndexesModuleComponent) {
        XmlSerializerUtil.copyBean(state, this)
    }
}

val Module.kotlinCompilerArgumentsCacheIndexesHolder: KotlinCompilerArgumentsCacheIndexesModuleComponent
    get() = this.getServiceSafe()