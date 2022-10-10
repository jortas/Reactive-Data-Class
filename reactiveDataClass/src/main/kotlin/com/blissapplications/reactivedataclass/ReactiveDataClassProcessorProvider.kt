package com.blissapplications.reactivedataclass

import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class ReactiveDataClassProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): ReactiveDataClassProcessor {
        return ReactiveDataClassProcessor(environment)
    }
}