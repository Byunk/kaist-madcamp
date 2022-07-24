import { v1 } from 'uuid';
import {
  existsSync,
  mkdirSync,
  writeFileSync,
  copyFileSync,
  rmdirSync,
} from 'fs';

export abstract class Execution {
  IMAGE_PREFIX_NAME = 'image-';
  EXECUTION_FOLDER_PREFIX_NAME = 'execution-';

  sourceCodeFile: any;
  language: string;
  timeLimit: number;
  memoryLimit: number;
  id: string;
  path: string;

  constructor(
    sourceCodeFile,
    language: string,
    timeLimit: number,
    memoryLimit: number,
  ) {
    this.sourceCodeFile = sourceCodeFile;
    this.language = language;
    this.timeLimit = timeLimit;
    this.memoryLimit = memoryLimit;

    this.id = v1().toString();
    this.path = 'data' + '/' + this.id;
  }

  createExecutionDirectory() {
    if (!existsSync(this.path)) {
      mkdirSync(this.path, { recursive: true });
      console.log('Saving uploaded files');
    }
    this._saveUploadedFiles();
    console.log('Copying Dockerfile to execution directory');
    this._copyDockerFileToExecutionDirectory();
  }

  deleteExecutionDirectory(): void {
    rmdirSync(this.path, { recursive: true });
  }

  getExecutionFolderName() {
    return this.EXECUTION_FOLDER_PREFIX_NAME + this.id;
  }

  getImageName() {
    return this.IMAGE_PREFIX_NAME + this.id;
  }

  _saveUploadedFiles(): void {
    //copyFileSync('src/main.py', this.path + '/main.py');
  }

  _copyDockerFileToExecutionDirectory() {
    copyFileSync(
      'src/dockerfiles/' + this.language + '_dockerfile',
      this.path + '/dockerfile',
    );
  }

  //abstract _addFilesIntoImage();

  //abstract getLanguage();
}
