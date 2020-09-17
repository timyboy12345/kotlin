/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.js.lower.serialization.ir

import org.jetbrains.kotlin.backend.common.serialization.DeclarationTable
import org.jetbrains.kotlin.backend.common.serialization.IrModuleSerializer
import org.jetbrains.kotlin.backend.common.serialization.signature.IdSignatureSerializer
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.descriptors.IrBuiltIns
import org.jetbrains.kotlin.ir.symbols.IrSymbol

class JsIrModuleSerializer(
    messageCollector: MessageCollector,
    irBuiltIns: IrBuiltIns,
    private val expectDescriptorToSymbol: MutableMap<DeclarationDescriptor, IrSymbol>,
    val skipExpects: Boolean
) : IrModuleSerializer<JsIrFileSerializer>(messageCollector) {

    private val signaturer = IdSignatureSerializer(JsManglerIr)
    private val globalDeclarationTable = JsGlobalDeclarationTable(signaturer, irBuiltIns)

    override fun createSerializerForFile(file: IrFile): JsIrFileSerializer =
        JsIrFileSerializer(messageCollector, DeclarationTable(globalDeclarationTable), expectDescriptorToSymbol, skipExpects = skipExpects)
}