import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Tag } from './entities/tag.entity';

@Injectable()
export class TagsService {
  constructor(@InjectRepository(Tag) private tagsRepository: Repository<Tag>) {
    this.tagsRepository = tagsRepository;
  }

  async saveTag(tag: Tag) {
    await this.tagsRepository.save(tag);
  }

  async deleteTag(tag: Tag) {
    await this.tagsRepository.delete(tag);
  }
}
