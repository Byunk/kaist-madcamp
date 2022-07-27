import { Injectable } from '@nestjs/common';
import { ContainerService } from './container.service';
import { exec, execSync, spawn, spawnSync } from 'child_process';
import { existsSync, mkdirSync, openSync } from 'fs';
import { v1 } from 'uuid';

@Injectable()
export class ContainerServiceDefault implements ContainerService {
  BUILD_TIMEOUT = 5 * 60000; // 5 minutes
  COMMAND_TIMEOUT = 2000;

  buildImage(folder: string, imageName: string) {
    //process.chdir(folder);
    let buildCommand = 'docker';
    let buildArgs: string[] = ['build', '-t', imageName, folder];

    //if (!existsSync('requirements.txt')) {
    //openSync('requirements.txt', 'w');
    //}

    let result = spawnSync(buildCommand, buildArgs);
    if (result.status == 0) {
      console.log(result.stdout.toString());
      console.log('Image build success!');
    } else {
      console.log(result.stderr.toString());
      throw 'Image build failed';
    }
  }

  runContainer(
    imageName: string,
    tagName: string,
    containerName: string,
    timeout: number,
  ) {
    let dockerCommand = 'docker';
    let dockerArgs: string[] = [
      'run',
      '-dit',
      '-p',
      '80:22',
      '--name',
      containerName,
      '-w',
      '/usr/src/app',
      imageName + ':' + tagName,
    ];

    let result = spawnSync(dockerCommand, dockerArgs);
    if (result.status == 0) {
      console.log('container name:' + result.stdout.toString());
      console.log('run success!');
    } else {
      console.log('container name err:' + result.stderr.toString());
      throw 'Container run failed';
    }

    let execResult = spawnSync('docker', [
      'exec',
      containerName,
      'service',
      'ssh',
      'start',
    ]);
    if (result.status == 0) {
      console.log(execResult.stdout.toString());
      console.log('ssh start success!');
    } else {
      console.log(execResult.stderr.toString());
      throw 'SSH start failed';
    }
  }

  uploadFileToContainer(srcDir: string, destDir: string) {
    let command = 'docker';
    let args: string[] = ['cp', srcDir, destDir];

    spawnSync(command, args);
    console.log('cp success!');
  }

  commitContainer(containerName: string, imageName: string): string {
    let tagId = v1().toString();
    let dockerCommand = 'docker';
    let dockerArgs: string[] = [
      'commit',
      containerName,
      imageName + ':' + tagId,
    ];

    spawnSync(dockerCommand, dockerArgs);
    console.log('commit success' + imageName + ':' + tagId);

    return tagId;
  }

  stopContainer(containerName: string) {
    let command: string = 'docker';
    let args: string[] = ['stop', containerName];

    let result = spawnSync(command, args);
    console.log(result.stdout.toString());
    console.log(result.stderr.toString());
    console.log('stop Container success');
  }

  deleteContainer(containerName: string) {
    let command: string = 'docker';
    let args: string[] = ['rm', containerName];

    let result = spawnSync(command, args);
    console.log(result.stdout.toString());
    console.log(result.stderr.toString());
    console.log('remove Container success');
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
