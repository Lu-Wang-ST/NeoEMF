#!/bin/bash

SLUG="atlanmod/NeoEMF"
JDK="oraclejdk8"
BRANCH="master"
OS="linux"

API_DIR="plugin"
ROOT_API_DIR="releases/snapshot"
TEMP_DIR="$HOME/$API_DIR"

if [ "$TRAVIS_REPO_SLUG" != "$SLUG" ]; then
  echo "Skipping update-site publication: wrong repository. Expected '$SLUG' but was '$TRAVIS_REPO_SLUG'."
elif [ "$TRAVIS_JDK_VERSION" != "$JDK" ]; then
  echo "Skipping update-site publication: wrong JDK. Expected '$JDK' but was '$TRAVIS_JDK_VERSION'."
elif [ "$TRAVIS_PULL_REQUEST" != "false" ]; then
  echo "Skipping update-site publication: was pull request."
elif [ "$TRAVIS_BRANCH" != "$BRANCH" ]; then
  echo "Skipping update-site publication: wrong branch. Expected '$BRANCH' but was '$TRAVIS_BRANCH'."
elif [ "$TRAVIS_OS_NAME" != "$OS" ]; then
  echo "Skipping update-site publication: wrong OS. Expected '$OS' but was '$TRAVIS_OS_NAME'."
else
    echo -e "Generating update-site..."

    mvn -B -q -f plugins/eclipse install

    if ! [ -d plugins/eclipse/update/target/repository ]; then
        echo -e "Skipping update-site publication: Update-site has not been built."
        exit
    fi

    echo -e "Copying update-site..."

    cp -R plugins/eclipse/update/target/repository ${TEMP_DIR}
    cd $HOME

    if ! [ -d "gh-pages" ]; then
        echo -e "Cloning 'gh-pages' branch..."

        git config --global user.email "travis@travis-ci.org"
        git config --global user.name "travis-ci"
        git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/${TRAVIS_REPO_SLUG} gh-pages
    fi

    echo -e "Merging update-site..."

    cd gh-pages

    mkdir -p ${ROOT_API_DIR}
    cd ${ROOT_API_DIR}

    if [ -d "$API_DIR" ]; then
        git rm --quiet -rf ${API_DIR}
    fi

    cp -Rf ${TEMP_DIR} ${API_DIR}

    if ! [ $(git diff --cached --exit-code --quiet) ]; then
        echo -e "Skipping update-site publication: no change."
        exit
    fi

    cp ../../updatesite/index.html ${API_DIR}

    echo -e "Publishing update-site..."

    git add -f .
    git commit --quiet -m "[auto] update the update-site from Travis build $TRAVIS_BUILD_NUMBER"
    git push --quiet -f origin gh-pages

    echo -e "Update-site published."
fi