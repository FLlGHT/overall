import {Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {IProfile} from "../../models/profile";
import {Chart} from "chart.js/auto";

@Component({
  selector: 'app-overall-chart',
  templateUrl: './overall-chart.component.html',
  styleUrls: ['./overall-chart.component.css']
})
export class OverallChartComponent implements OnInit, OnChanges {

  @Input() value: any

  public chart: any

  ngOnInit(): void {
    this.createChart()
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.createChart()
  }

  createChart() {
    this.initChart();
  }

  initChart() {
    if (this.chart)
      this.chart.destroy()

    let overall = (this.value) ? this.value : 0
    let data = {
      datasets: [{
        data: [overall, 99 - overall],
        backgroundColor: ["#2d6be1", "#e3e3e3"]
      }]
    }


    this.chart = new Chart("Overall",
      {
        type: 'doughnut',
        data: data,
        options: {
          cutout: '66%'
        },
        plugins: [{
          id: "Overall",
          beforeDraw: () => this.beforeDraw()
        }]
      }
    )
  }

  beforeDraw() {
    this.createGradient()
    this.addCenterText()
  }

  addCenterText() {
    let width = this.chart.width,
      height = this.chart.height,
      ctx = this.chart.ctx;

    ctx.restore();
    let fontSize = (height / 50).toFixed(2);
    ctx.font = fontSize + "em sans-serif";
    ctx.textBaseline = "middle";

    let text = this.value.toString(),
      textX = Math.round((width - ctx.measureText(text).width) / 2),
      textY = height / 2 + this.chart.legend.height + this.chart.titleBlock.height - height/30;

    ctx.fillStyle = '#061a5e'
    ctx.fillText(text, textX, textY);
    ctx.save();
  }

  createGradient() {
    let gradient = this.chart.ctx.createLinearGradient(0, 0, 0, 220);
    gradient.addColorStop(0, '#6b8dff');
    gradient.addColorStop(0.5, '#21367e');
    gradient.addColorStop(1, '#061a5e');

    this.chart.data.datasets[0].backgroundColor = [gradient, "#e3e3e3"]
    this.chart.update()
  }
}
