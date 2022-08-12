FROM node:16.16
WORKDIR ./

COPY package.json .

ADD . .
RUN npm install 
RUN npm run lint -- --fix

EXPOSE 8081

CMD ["npm", "run", "serve"]
