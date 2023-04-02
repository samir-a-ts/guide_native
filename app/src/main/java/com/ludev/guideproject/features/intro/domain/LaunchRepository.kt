package com.ludev.guideproject.features.intro.domain

/// Interface representing data
/// about previous runs.
interface ILaunchRepository {

    /// Whether this app was already launched.
    fun isFirstLaunch(): Boolean

}