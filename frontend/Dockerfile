FROM node:18-alpine3.15 as builder

WORKDIR /app
COPY . /app

RUN ls

RUN npm install
RUN npm run build

FROM nginx:alpine

COPY --from=builder /app/build/  /usr/share/nginx/html
COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf

