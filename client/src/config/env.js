// src/env.js
// export const API_URL = import.meta.env.LOCALHOST;
// export const API_URL = `http://192.168.137.36:8085`;

// Constants.js
const HOST = `http://localhost:8085`;
const AUTH = "/auth";
const LOGIN = "/login";
const STUDENT = "/student";
const STATUS = "/status";
const status = HOST + STUDENT + STATUS;
const studentDetail = HOST + STUDENT + "/detail";


export const API = {
   HOST,
   AUTH,
   LOGIN,
   STUDENT,
   status,
   studentDetail,
};
