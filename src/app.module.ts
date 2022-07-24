import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { CompilerController } from './controller/compiler.controller';
import { CompilerServiceDefault } from './service/compiler.service.default';
import { ContainerServiceDefault } from './service/container.service.default';

@Module({
  imports: [],
  controllers: [CompilerController],
  providers: [CompilerServiceDefault, ContainerServiceDefault],
})
export class AppModule {}
