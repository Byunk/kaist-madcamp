import { CompileRequestDto } from 'src/compilers/dto/compile.request.dto';
import { Execution } from './Execution';

export class NodeExecution extends Execution {
  constructor(req: CompileRequestDto) {
    super(req);
  }

  getLanguage(): string {
    return 'node';
  }
}
