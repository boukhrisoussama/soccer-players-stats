import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-video-player-dialog',
  template: `
    <div class="video-container">
      <iframe
        [src]="safeVideoUrl"
        frameborder="0"
        allowfullscreen>
      </iframe>
    </div>
  `,
  styles: [`
    .video-container {
      position: relative;
      padding-bottom: 56.25%; /* 16:9 aspect ratio */
      height: 0;
      overflow: hidden;

      iframe {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
      }
    }
  `]
})
export class VideoPlayerDialogComponent {
  safeVideoUrl: any;

  constructor(
    public dialogRef: MatDialogRef<VideoPlayerDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { videoUrl: string }
  ) {
    // Note: In a real app, you'd need to sanitize the URL using DomSanitizer
    this.safeVideoUrl = data.videoUrl;
  }
}
