application="kiilin-sensitive-demo"

date=$(date "+%Y%m%d%H%M%S")

docker build -t ${application}:${date} .
docker build -t ${application} .

docker rm -f ${application}


docker run \
--restart=always \
--name ${application} \
-d \
-p 8080:8080 \
${application}
