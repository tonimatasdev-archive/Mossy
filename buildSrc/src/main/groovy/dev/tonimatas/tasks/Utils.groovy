package dev.tonimatas.tasks

class Utils {
    static def getDependencies(config) {
        def ret = []

        config.resolvedConfiguration.resolvedArtifacts.each {
            ret.add(it.moduleVersion.id.group + ':' + it.moduleVersion.id.name + ":" + it.moduleVersion.id.version)
        }

        return ret
    }

    static def getRepositories(handler) {
        def repositories = []

        handler.each {
            repositories.add(it.url)
        }

        return repositories
    }
}
