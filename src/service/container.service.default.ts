import { Injectable } from '@nestjs/common';
import { ContainerService } from './container.service';
import { spawn, spawnSync } from 'child_process';
import { existsSync, mkdirSync, openSync } from 'fs';

@Injectable()
export class ContainerServiceDefault implements ContainerService {
  BUILD_TIMEOUT = 5 * 60000; // 5 minutes
  COMMAND_TIMEOUT = 2000;

  buildImage(folder: string, imageName: string) {
    process.chdir(folder);
    console.log('New directory: ' + process.cwd());
    console.log(folder);
    let buildCommand = 'docker';
    let buildArgs: string[] = ['build', '-t', imageName, '.'];

    //if (!existsSync('requirements.txt')) {
    //openSync('requirements.txt', 'w');
    //}

    let result = spawnSync(buildCommand, buildArgs);
    console.log(result.stdout.toString());
    console.log(result.stderr.toString());
    console.log('build success');
  }

  runContainer(imageName: string, timeout: number): string {
    let dockerCommand = 'docker';
    let dockerArgs: string[] = ['run', '-dit', imageName];

    let result = spawnSync(dockerCommand, dockerArgs);
    console.log(result.stdout.toString());
    //console.log(result.stderr.toString());
    console.log('run success');
    return result.stdout.toString();
  }

  stopContainer(containerName: string) {
    let command: string = 'docker';
    let args: string[] = ['stop', containerName];

    let result = spawnSync(command, args);
    console.log(result.stdout.toString());
    console.log(result.stderr.toString());
    console.log('stop success');
  }

  execCommand(containerName: string, command: string[]): string {
    let dockerCommand: string = 'docker';
    let args: string[] = ['exec', containerName].concat(command);

    let result = spawnSync(dockerCommand, args);
    console.log(result.stdout.toString());
    console.log(result.stderr.toString());
    return result.stdout.toString();
  }

  getRunningContainer() {
    let command: string = 'docker';
    let args: string[] = ['ps'];

    let result = spawnSync(command, args);
    console.log(result.stdout.toString());
    console.log('get containers success');
  }

  getImages() {
    let command: string = 'docker';
    let args: string[] = ['images'];

    let result = spawnSync(command, args);
    console.log(result.stdout.toString());
    console.log('get image success');
  }

  deleteImage(imageName: string) {
    let command: string = 'docker';
    let args: string[] = ['rmi', '-f', imageName];

    spawnSync(command, args);
    console.log('delete image success');
  }
}
