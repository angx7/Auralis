// ui/screens/sessions/components/PrecisionChartCard.kt
package com.example.cameratest.ui.screens.sessions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
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
import co.yml.charts.ui.linechart.model.*
import com.example.cameratest.ui.theme.BrainCircuit
import com.example.cameratest.ui.theme.CameraTestTheme

@Composable
fun PrecisionChartCard(
    resumen: String,
    chartValues: List<Float>,
    precisionActual: Int?
) {
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

            // HEADER
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

            // TEXTO DEL INFORME (del backend)
            Text(
                text = "Resumen de tus sesiones:",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = resumen,
                style = MaterialTheme.typography.bodyMedium,
                color = colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(24.dp))

            PrecisionChartSection(
                values = chartValues,
                precisionActual = precisionActual
            )
        }
    }
}

@Composable
private fun PrecisionChartSection(
    values: List<Float>,
    precisionActual: Int?
) {
    val colorScheme = MaterialTheme.colorScheme

    if (values.isEmpty()) {
        // Estado vacío
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Aún no hay suficientes datos para mostrar tu gráfica.",
                style = MaterialTheme.typography.bodySmall,
                color = colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
        return
    }

    val points = values.mapIndexed { index, v -> Point(index.toFloat(), v) }
    val lastPoint = points.last()
    val lineColor = colorScheme.primary

    // Eje X sin labels visibles
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
        .steps(6)
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            val valueToShow = precisionActual ?: lastPoint.y.toInt()
            Text(
                text = "${valueToShow}%",
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
                .padding(start = 16.dp, end = 16.dp)
                .background(colorScheme.surface),
            lineChartData = lineChartData
        )
    }
}

@Preview
@Composable
fun PrecisionChartCardPreview() {
    CameraTestTheme(darkTheme = false) {
        PrecisionChartCard(
            resumen = "Ejemplo de resumen de IA.",
            chartValues = listOf(78f, 80f, 82f, 85f, 87f, 90f),
            precisionActual = 90
        )
    }
}