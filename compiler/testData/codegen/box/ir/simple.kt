interface BranchInfo {
    val ref: String
}

interface PR_RepositoryInfo

interface PR_ProjectComplete

interface IVCSPath {
    interface InRepo : Repository {
        val path: String
    }

    interface InRevision : Revision, InRepo

    interface InBranch : Branch, InRevision

    interface Revision : Repository {
        val commit: String
    }

    interface Branch : Repository, Revision {
        val branch: BranchInfo
        override val commit
            get() = branch.ref
    }

    interface Repository : Project {
        val repo: PR_RepositoryInfo
    }

    interface Project : IVCSPath {
        val projectComplete: PR_ProjectComplete
    }
}

class Test(val ok: String)

fun box() = Test("OK").ok