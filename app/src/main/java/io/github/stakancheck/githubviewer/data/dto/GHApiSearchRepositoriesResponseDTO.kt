/*
 * Copyright (c) 2025 Artem Sukhanov (Stakancheck)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.stakancheck.githubviewer.data.dto


import io.github.stakancheck.githubviewer.data.dto.common.TextMatchDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GHApiSearchRepositoriesResponseDTO(
    @SerialName("total_count") val totalCount: Int,
    @SerialName("incomplete_results") val incompleteResults: Boolean,
    val items: List<RepositoryDTO>
)

@Serializable
data class RepositoryDTO(
    val id: Int,
    @SerialName("node_id") val nodeId: String,
    val name: String,
    @SerialName("full_name") val fullName: String,
    val owner: UserDTO?,
    val private: Boolean,
    @SerialName("html_url") val htmlUrl: String,
    val description: String?,
    val fork: Boolean,
    val url: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("pushed_at") val pushedAt: String,
    val homepage: String?,
    val size: Int,
    @SerialName("stargazers_count") val stargazersCount: Int,
    @SerialName("watchers_count") val watchersCount: Int,
    val language: String?,
    @SerialName("forks_count") val forksCount: Int,
    @SerialName("open_issues_count") val openIssuesCount: Int,
    @SerialName("master_branch") val masterBranch: String?,
    @SerialName("default_branch") val defaultBranch: String,
    val score: Double,
    @SerialName("forks_url") val forksUrl: String,
    @SerialName("keys_url") val keysUrl: String,
    @SerialName("collaborators_url") val collaboratorsUrl: String,
    @SerialName("teams_url") val teamsUrl: String,
    @SerialName("hooks_url") val hooksUrl: String,
    @SerialName("issue_events_url") val issueEventsUrl: String,
    @SerialName("events_url") val eventsUrl: String,
    @SerialName("assignees_url") val assigneesUrl: String,
    @SerialName("branches_url") val branchesUrl: String,
    @SerialName("tags_url") val tagsUrl: String,
    @SerialName("blobs_url") val blobsUrl: String,
    @SerialName("git_tags_url") val gitTagsUrl: String,
    @SerialName("git_refs_url") val gitRefsUrl: String,
    @SerialName("trees_url") val treesUrl: String,
    @SerialName("statuses_url") val statusesUrl: String,
    @SerialName("languages_url") val languagesUrl: String,
    @SerialName("stargazers_url") val stargazersUrl: String,
    @SerialName("contributors_url") val contributorsUrl: String,
    @SerialName("subscribers_url") val subscribersUrl: String,
    @SerialName("subscription_url") val subscriptionUrl: String,
    @SerialName("commits_url") val commitsUrl: String,
    @SerialName("git_commits_url") val gitCommitsUrl: String,
    @SerialName("comments_url") val commentsUrl: String,
    @SerialName("issue_comment_url") val issueCommentUrl: String,
    @SerialName("contents_url") val contentsUrl: String,
    @SerialName("compare_url") val compareUrl: String,
    @SerialName("merges_url") val mergesUrl: String,
    @SerialName("archive_url") val archiveUrl: String,
    @SerialName("downloads_url") val downloadsUrl: String,
    @SerialName("issues_url") val issuesUrl: String,
    @SerialName("pulls_url") val pullsUrl: String,
    @SerialName("milestones_url") val milestonesUrl: String,
    @SerialName("notifications_url") val notificationsUrl: String,
    @SerialName("labels_url") val labelsUrl: String,
    @SerialName("releases_url") val releasesUrl: String,
    @SerialName("deployments_url") val deploymentsUrl: String,
    @SerialName("git_url") val gitUrl: String,
    @SerialName("ssh_url") val sshUrl: String,
    @SerialName("clone_url") val cloneUrl: String,
    @SerialName("svn_url") val svnUrl: String,
    val forks: Int,
    @SerialName("open_issues") val openIssues: Int,
    val watchers: Int,
    val topics: List<String>?,
    @SerialName("mirror_url") val mirrorUrl: String?,
    @SerialName("has_issues") val hasIssues: Boolean,
    @SerialName("has_projects") val hasProjects: Boolean,
    @SerialName("has_pages") val hasPages: Boolean,
    @SerialName("has_wiki") val hasWiki: Boolean,
    @SerialName("has_downloads") val hasDownloads: Boolean,
    @SerialName("has_discussions") val hasDiscussions: Boolean,
    val archived: Boolean,
    val disabled: Boolean,
    val visibility: String?,
    val license: LicenseDTO?,
    val permissions: PermissionsDTO?,
    @SerialName("text_matches") val textMatches: List<TextMatchDTO>?,
    @SerialName("temp_clone_token") val tempCloneToken: String?,
    @SerialName("allow_merge_commit") val allowMergeCommit: Boolean,
    @SerialName("allow_squash_merge") val allowSquashMerge: Boolean,
    @SerialName("allow_rebase_merge") val allowRebaseMerge: Boolean,
    @SerialName("allow_auto_merge") val allowAutoMerge: Boolean,
    @SerialName("delete_branch_on_merge") val deleteBranchOnMerge: Boolean,
    @SerialName("allow_forking") val allowForking: Boolean,
    @SerialName("is_template") val isTemplate: Boolean,
    @SerialName("web_commit_signoff_required") val webCommitSignoffRequired: Boolean
)

@Serializable
data class UserDTO(
    val name: String?,
    val email: String?,
    val login: String,
    val id: Long,
    @SerialName("node_id") val nodeId: String,
    @SerialName("avatar_url") val avatarUrl: String,
    @SerialName("gravatar_id") val gravatarId: String?,
    val url: String,
    @SerialName("html_url") val htmlUrl: String,
    @SerialName("followers_url") val followersUrl: String,
    @SerialName("following_url") val followingUrl: String,
    @SerialName("gists_url") val gistsUrl: String,
    @SerialName("starred_url") val starredUrl: String,
    @SerialName("subscriptions_url") val subscriptionsUrl: String,
    @SerialName("organizations_url") val organizationsUrl: String,
    @SerialName("repos_url") val reposUrl: String,
    @SerialName("events_url") val eventsUrl: String,
    @SerialName("received_events_url") val receivedEventsUrl: String,
    val type: String,
    @SerialName("site_admin") val siteAdmin: Boolean,
    @SerialName("starred_at") val starredAt: String?,
    @SerialName("user_view_type") val userViewType: String?
)

@Serializable
data class LicenseDTO(
    val key: String,
    val name: String,
    val url: String?,
    @SerialName("spdx_id") val spdxId: String?,
    @SerialName("node_id") val nodeId: String,
    @SerialName("html_url") val htmlUrl: String?
)

@Serializable
data class PermissionsDTO(
    val admin: Boolean,
    val maintain: Boolean,
    val push: Boolean,
    val triage: Boolean,
    val pull: Boolean
)
