# Lil playground

![GitHub Workflow Status (with event)](https://img.shields.io/github/actions/workflow/status/lilgallon/lil-playground/.github%2Fworkflows%2Fci.yml?style=for-the-badge&logo=gradle&label=CI&link=https%3A%2F%2Fgithub.com%2Flilgallon%2Flil-playground%2Factions%2Fworkflows%2Fci.yml)
![GitHub Workflow Status (with event)](https://img.shields.io/github/actions/workflow/status/lilgallon/lil-playground/.github%2Fworkflows%2Fcd.yml?style=for-the-badge&logo=gradle&label=CD&link=https%3A%2F%2Fgithub.com%2Flilgallon%2Flil-playground%2Factions%2Fworkflows%2Fcd.yml)
![GitHub milestone](https://img.shields.io/github/milestones/progress-percent/lilgallon/lil-playground/1?style=for-the-badge)

![Sonar Quality Gate](https://img.shields.io/sonar/quality_gate/lilgallon_lil-playground/main?server=https%3A%2F%2Fsonarcloud.io&style=for-the-badge&logo=sonarcloud)
![Sonar Bugs](https://img.shields.io/sonar/bugs/lilgallon_lil-playground/main?server=https%3A%2F%2Fsonarcloud.io&style=for-the-badge&logo=sonarcloud)
![Sonar Code Smells](https://img.shields.io/sonar/code_smells/lilgallon_lil-playground/main?server=https%3A%2F%2Fsonarcloud.io&style=for-the-badge&logo=sonarcloud)

A place where I learn about advanced topics in a funny way. I also comment every
part of the code to make sure I understand what I am doing.

**I may be wrong on some statements, I'm still learning, do not take everything
you read as true**

## 1. Challenges

### 1.1. Gradle

This project has to follow all the best practices of gradle to have the best
possible performance.

**template:** I templated the project using https://gitlab.com/opensavvy/playgrounds/gradle
that already respect the best practices.

**Gradle Enterprise using version catalog:** On top of that, I added Gradle
Enterprise plugin by using the version catalog. It's not that easy because the
catalog feature is designed so that a settings plugin could define a version
catalog. But that means that the version catalog can not define settings plugins
because for that the version catalog would need to be available before the
plugins are evaluated.

The workaround is to create a custom settings plugin where we use the gradle
enterprise plugin. Then we use our custom plugin in the root [settings.gradle.kts](settings.gradle.kts).
Everything is in [commit 831dfd](https://github.com/lilgallon/lil-playground/commit/831dfd2bd2d640f349527b0ecff3636576d46e9e).

### 1.2. Github workflows

#### Challenge

Everything that can be automated, has to be automated using github workflows.
Some examples:
- CI that comment the PR if there is an issue. The CI has to be optimized by
using gradle caching
- CD that creates a Github release from a tag. In that release we need to have
the artefact and the changelog

#### Solution

**CI:** We can use the [github cache action](https://github.com/actions/cache).
But the best way to have caching for gradle in Github Workflows is to use
[gradle build action](https://github.com/gradle/gradle-build-action).

**CD:** It automatically builds the app when a tag is pushed on the repo. The
tag as to respect a regex though. A github release will then be created with
a changelog and the built artefacts.

### 1.3. Github projects

All the tasks and issues will be traced using [Github Projects](https://github.com/users/lilgallon/projects/5).

### 1.4. Coroutines

I need to learn more about Kotlin Coroutines. The best way for it is to do heavy
parallel tasks. There are two interested things to do:
- Fractal Viewer
- Simple Physics Engine

I will use JavaFX, because the API is nice to use with Kotlin, and it's easy to
code a GUI. It also has support to render scenes.
