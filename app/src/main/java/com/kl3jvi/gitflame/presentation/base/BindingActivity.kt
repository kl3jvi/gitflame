package com.kl3jvi.gitflame.presentation.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BindingActivity<T : ViewDataBinding> constructor(
    @LayoutRes private val contentLayoutId: Int
) : AppCompatActivity() {

    /** This interface is generated during compilation to contain getters for all used instance `BindingAdapters`. */
    protected var bindingComponent: DataBindingComponent? = DataBindingUtil.getDefaultComponent()

    /**
     * A data-binding property will be initialized before being called [onCreate].
     * And inflates using the [contentLayoutId] as a content view for activities.
     */
    protected val binding: T by lazy(LazyThreadSafetyMode.NONE) {
        DataBindingUtil.setContentView(this, contentLayoutId, bindingComponent)
    }

    /**
     * An executable inline binding function that receives a binding receiver in lambda.
     *
     * @param block A lambda block will be executed with the binding receiver.
     * @return T A generic class that extends [ViewDataBinding] and generated by DataBinding on compile time.
     */
    protected inline fun binding(block: T.() -> Unit): T {
        return binding.apply(block)
    }

    /**
     * Ensures the [binding] property should be executed before being called [onCreate].
     */
    init {
        addOnContextAvailableListener {
            binding.notifyChange()
        }
    }

    /**
     * Removes binding listeners to expression variables and destroys the [binding] backing property for preventing
     * leaking the [ViewDataBinding] that references the Context.
     */
    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}