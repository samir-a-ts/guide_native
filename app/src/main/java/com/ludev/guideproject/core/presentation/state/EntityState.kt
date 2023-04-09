package com.ludev.guideproject.core.presentation.state

/// Describes UI state of each individual field
/// in UI state.
abstract class EntityState<T>(var value: T?) {
    fun content(newValue: T?): EntityState<T> {
        if (value == null) return EmptyEntityState()

        if (value == newValue) return ContentEntityState(value)

        return ContentEntityState(newValue)
    }

    fun loading(): EntityState<T> {
        return LoadingEntityState(value)
    }

    fun error(message: String): EntityState<T> {
        return ErrorEntityState(message, value)
    }
}

class EmptyEntityState<T>() : EntityState<T>(null)

class ContentEntityState<T>(value: T?) : EntityState<T>(value)

class LoadingEntityState<T>(value: T?) : EntityState<T>(value)

class ErrorEntityState<T>(val message: String, value: T?) : EntityState<T>(value)
