package dev.tonimatas.tasks

class Artifacts {
    static def getDependencies(config) {
        def ret = []

        config.resolvedConfiguration.resolvedArtifacts.each {
            ret.add(new Artifact(it.moduleVersion.id.group + ':' + it.moduleVersion.id.name, it.moduleVersion.id.version))
        }

        return ret
    }
}
