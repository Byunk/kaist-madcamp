import { existsSync, mkdirSync } from 'fs';
import { diskStorage } from 'multer';
//import getProcessEnv from "./getProcessEnv";

export const multerOptions = {
  storage: diskStorage({
    destination: (request, file, callback) => {
      const uploadPath: string = 'static';

      if (!existsSync(uploadPath)) {
        // static 폴더가 존재하지 않을시, 생성합니다.
        mkdirSync(uploadPath);
      }

      callback(null, uploadPath);
    },
  }),
};
/*
export const createUrl = (file): string => {
  //const serverAddress: string = getProcessEnv('SERVER_ADDRESS');
  // 파일이 저장되는 경로: 서버주소/public 폴더
  // 위의 조건에 따라 파일의 경로를 생성해줍니다.
  //return `${serverAddress}/public/${file.filename}`;
};*/
