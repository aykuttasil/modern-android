package com.aykuttasil.sweetloc.di.scopes

import javax.inject.Scope

/**
 * Created by aykutasil on 9.12.2017.
 */
/**
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the Activity to be memorised in the
 * correct component.
 */
@Scope
@Retention annotation class PerActivity