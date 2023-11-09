package dev.tonimatas.tasks

class Artifact {
    final dependency
    final version

    Artifact(String dependency, String version) {
        this.dependency = dependency
        this.version = version
    }

    String get() {
        return dependency + ":" + version
    }

    String getDependency() {
        return dependency
    }

    String getVersion() {
        return version
    }
}
