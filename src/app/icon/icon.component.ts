import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-icon',
  templateUrl: './icon.component.html',
  styleUrls: ['./icon.component.css'],
})
export class IconComponent {
  @Input() iconName: string = '';
  @Input() iconClasses: string | null = null;
  @Input() iconViewBox: string | null = null;

  get resolvedViewBox(): string {
    return this.iconViewBox || '0 0 20 20';
  }
}
