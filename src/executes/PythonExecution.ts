import { Execution } from './Execution';

export class PythonExecution extends Execution {
  //COMPILATION_COMMAND = 'python';
  //SOURCE_CODE_FILE_NAME = 'main.py';

  constructor(
    sourceCode,
    language: string,
    timeLimit: number,
    memoryLimit: number,
  ) {
    super(sourceCode, language, timeLimit, memoryLimit);
  }

  /*_addFilesIntoImage() {
    // Docker cp
  }*/
}
