/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.compiler.configuration

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.kotlin.idea.util.application.getServiceSafe

@State(name = "KotlinCompilerArgumentsCacheStorage", storages = [Storage(file = "compiler-arg-caches.xml")])
class KotlinCompilerArgumentsCacheProjectComponent : PersistentStateComponent<KotlinCompilerArgumentsCacheProjectComponent> {
    var idToCompilerArguments: Map<Int, String> = HashMap()

    override fun getState(): KotlinCompilerArgumentsCacheProjectComponent? = this

    override fun loadState(state: KotlinCompilerArgumentsCacheProjectComponent) {
        XmlSerializerUtil.copyBean(state, this)

    }
}

val Project.kotlinCompilerArgumentsCacheStorage: KotlinCompilerArgumentsCacheProjectComponent
    get() = this.getServiceSafe()