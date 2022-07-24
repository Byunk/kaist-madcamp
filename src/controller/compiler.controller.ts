import {
  Body,
  Controller,
  Get,
  Param,
  Post,
  UploadedFiles,
  UseInterceptors,
} from '@nestjs/common';
import { FileInterceptor } from '@nestjs/platform-express';
import { CExecution } from 'src/executes/CExecution';
import { Execution } from 'src/executes/Execution';
import { JavaExecution } from 'src/executes/JavaExecution';
import { PythonExecution } from 'src/executes/PythonExecution';
import { Language } from 'src/models/Language';
import { CompilerServiceDefault } from 'src/service/compiler.service.default';

@Controller('compiler')
export class CompilerController {
  constructor(private compilerService: CompilerServiceDefault) {
    this.compilerService = compilerService;
  }

  @Get('compile/:language')
  //@UseInterceptors(FileInterceptor('files'))
  async compile(@Param('language') language) {
    // Language Validation
    if (!Object.values(Language).includes(language)) {
      throw 'Invalid Language Error';
    }

    var exec: Execution;

    switch (language) {
      case 'python':
        exec = new PythonExecution(null, language, null, null);
        break;
      case 'java':
        exec = new JavaExecution(null, language, null, null);
        break;
      case 'c':
        exec = new CExecution(null, language, null, null);
        break;
      default:
        throw 'Invalid Language Error';
    }

    this.compilerService.compile(exec);
    return;
  }
}
