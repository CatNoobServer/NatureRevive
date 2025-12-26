rootProject.name = "NatureReviveRewrite"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

include("naturerevive-common")

listOf("1_21", "1_21_3", "1_21_4", "1_21_5", "1_21_7", "1_21_9", "1_21_11", "compat").forEach {
    include(":naturerevive-spigot:nms:nms-$it")

    findProject(":naturerevive-spigot:nms:nms-$it")?.name = "nms-$it"
}

include("naturerevive-spigot")
