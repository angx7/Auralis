package com.example.cameratest.ui.screens.sessions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.example.cameratest.ui.theme.BrainCircuit
import com.example.cameratest.ui.theme.CameraTestTheme

// =====================================================================
//  CARD GRANDE: Informe de IA + Gráfica
// =====================================================================
@Composable
fun PrecisionChartCard() {
    val colorScheme = MaterialTheme.colorScheme

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ========== HEADER DEL INFORME ==========
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(74.dp)
                        .background(
                            color = colorScheme.primary.copy(alpha = 0.12f),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = BrainCircuit,
                        contentDescription = null,
                        tint = colorScheme.primary,
                        modifier = Modifier.size(52.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Tu Informe de IA",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = colorScheme.onSurface
                    )
                    Text(
                        text = "Personalizado",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ========== TEXTO DEL INFORME ==========
            Text(
                text = "¡Increíble avance, Alex!",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "En el último mes, la IA ha detectado una mejora del 15% en tu " +
                        "precisión rítmica, especialmente en piezas de Mozart. Tu control " +
                        "de la dinámica ha sido sobresaliente en pasajes suaves. Sigue así, " +
                        "tu constancia está dando frutos.",
                style = MaterialTheme.typography.bodyMedium,
                color = colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ========== SECCIÓN DE LA GRÁFICA ==========
            PrecisionChartSection()
        }
    }
}

// =====================================================================
//  SOLO la sección de la gráfica (sin Card externa)
// =====================================================================
@Composable
private fun PrecisionChartSection() {
    val colorScheme = MaterialTheme.colorScheme

    // Datos mock
    val values = listOf(72f, 78f, 80f, 84f, 88f, 92f)
    val points = values.mapIndexed { index, v -> Point(index.toFloat(), v) }
    val lastPoint = points.last()
    val lineColor = colorScheme.primary

    // Eje X (sin labels)
    val xAxisData = AxisData.Builder()
        .axisStepSize(40.dp)
        .steps(points.size - 1)
        .labelData { "" }
        .axisLineColor(Color.Transparent)
        .axisLabelColor(Color.Transparent)
        .startDrawPadding(24.dp)
        .build()

    // Eje Y sin labels visibles (solo gridlines)
    val yAxisData = AxisData.Builder()
        .steps(4)
        .axisOffset(50.dp)
        .labelData { "" }
        .axisLineColor(Color.Transparent)
        .axisLabelColor(Color.Transparent)
        .build()

    val line = Line(
        dataPoints = points,
        lineStyle = LineStyle(
            color = lineColor,
            width = 3f
        ),
        intersectionPoint = IntersectionPoint(
            color = lineColor,
            radius = 4.dp
        ),
        shadowUnderLine = ShadowUnderLine(
            alpha = 0.4f,
            brush = Brush.verticalGradient(
                colors = listOf(
                    lineColor.copy(alpha = 0.45f),
                    lineColor.copy(alpha = 0.05f)
                )
            )
        )
    )

    val gridLines = GridLines(
        enableVerticalLines = false,
        enableHorizontalLines = true,
        color = colorScheme.onSurface.copy(alpha = 0.08f),
        lineWidth = 1.dp
    )

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(lines = listOf(line)),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = gridLines,
        backgroundColor = colorScheme.surface
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Precisión Rítmica Últimas Sesiones",
            style = MaterialTheme.typography.titleSmall,
            color = colorScheme.onSurface
        )
        // Texto 92% arriba centrado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${lastPoint.y.toInt()}%",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = lineColor
            )
        }

        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(start = 16.dp, end = 8.dp)
                .background(colorScheme.surface),
            lineChartData = lineChartData
        )
    }
}

@Preview
@Composable
fun PrecisionChartCardPreview() {
    CameraTestTheme(darkTheme = false) {
        PrecisionChartCard()
    }
}