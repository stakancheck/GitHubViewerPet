package io.github.stakancheck.githubviewer.ui.icons

import androidx.compose.ui.graphics.vector.ImageVector
import io.github.stakancheck.githubviewer.ui.icons.iconpack.ArrowLeft
import io.github.stakancheck.githubviewer.ui.icons.iconpack.ChevronRight
import io.github.stakancheck.githubviewer.ui.icons.iconpack.Eye
import io.github.stakancheck.githubviewer.ui.icons.iconpack.File
import io.github.stakancheck.githubviewer.ui.icons.iconpack.Folder
import io.github.stakancheck.githubviewer.ui.icons.iconpack.GitFork
import io.github.stakancheck.githubviewer.ui.icons.iconpack.Search
import io.github.stakancheck.githubviewer.ui.icons.iconpack.Star
import io.github.stakancheck.githubviewer.ui.icons.iconpack.X
import kotlin.collections.List as ____KtList

public object IconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val IconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Search, File, X, ArrowLeft, ChevronRight, Star, Eye, Folder, GitFork)
    return __AllIcons!!
  }
