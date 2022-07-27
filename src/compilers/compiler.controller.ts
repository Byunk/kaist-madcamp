import {
  Bind,
  Body,
  Controller,
  Param,
  Post,
  UploadedFiles,
  UseInterceptors,
} from '@nestjs/common';
import { NestFactory } from '@nestjs/core';
import {
  FileInterceptor,
  NestExpressApplication,
} from '@nestjs/platform-express';
import { AppModule } from 'src/app.module';
import { CompileRequestDto } from 'src/compilers/dto/compile.request.dto';
import { CExecution } from 'src/compilers/executes/CExecution';
import { Execution } from 'src/compilers/executes/Execution';
import { JavaExecution } from 'src/compilers/executes/JavaExecution';
import { PythonExecution } from 'src/compilers/executes/PythonExecution';
import { CompilerServiceDefault } from './compiler.service.default';

@Controller('compiler')
export class CompilerController {
  constructor(private compilerService: CompilerServiceDefault) {
    this.compilerService = compilerService;
  }

  @Post('compile')
  async compile(@Body() req: CompileRequestDto) {
    var exec: Execution;

    const app = await NestFactory.create<NestExpressApplication>(AppModule);
    app.enableCors();
    await app.listen(3000);

    switch (req.language) {
      case 'python':
        exec = new PythonExecution(req);
        break;
      case 'java':
        exec = new JavaExecution(req);
        break;
      case 'c':
        exec = new CExecution(req);
        break;
      default:
        throw 'Invalid Language Error';
    }

    this.compilerService.compile(exec);
    return;
  }

  @Post('upload')
  @UseInterceptors(FileInterceptor('file'))
  uploadFile(file) {
    console.log(file);
  }
}
