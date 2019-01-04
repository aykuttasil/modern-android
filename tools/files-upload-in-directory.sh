#!/bin/bash
for file in ./*
do
    curl -X POST "https://${BB_AUTH_STRING}@api.bitbucket.org/2.0/repositories/${BITBUCKET_REPO_OWNER}/${BITBUCKET_REPO_SLUG}/downloads" --form files=@$file
done
