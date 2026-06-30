package com.example.simplepaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PaintView extends View {

    public enum ShapeType {
        FREE_HAND, RECTANGLE, CIRCLE, LINE
    }

    private ShapeType currentShape = ShapeType.FREE_HAND;
    private int currentColor = Color.BLACK;
    private float strokeWidth = 10f;

    private List<DrawingShape> shapes = new ArrayList<>();
    private DrawingShape currentDrawingShape;

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setShapeType(ShapeType shapeType) {
        this.currentShape = shapeType;
    }

    public void setColor(int color) {
        this.currentColor = color;
    }

    public void clear() {
        shapes.clear();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (DrawingShape shape : shapes) {
            shape.draw(canvas);
        }

        if (currentDrawingShape != null) {
            currentDrawingShape.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                performClick();
                startTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                upTouch(x, y);
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    private void startTouch(float x, float y) {
        Paint paint = new Paint();
        paint.setColor(currentColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setAntiAlias(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);

        currentDrawingShape = new DrawingShape(currentShape, paint, x, y);
        if (currentShape == ShapeType.FREE_HAND) {
            currentDrawingShape.path.moveTo(x, y);
        }
    }

    private void moveTouch(float x, float y) {
        if (currentShape == ShapeType.FREE_HAND) {
            currentDrawingShape.path.lineTo(x, y);
        } else {
            currentDrawingShape.endX = x;
            currentDrawingShape.endY = y;
        }
    }

    private void upTouch(float x, float y) {
        if (currentShape == ShapeType.FREE_HAND) {
            currentDrawingShape.path.lineTo(x, y);
        } else {
            currentDrawingShape.endX = x;
            currentDrawingShape.endY = y;
        }
        shapes.add(currentDrawingShape);
        currentDrawingShape = null;
    }

    private static class DrawingShape {
        ShapeType type;
        Paint paint;
        Path path;
        float startX, startY, endX, endY;

        DrawingShape(ShapeType type, Paint paint, float x, float y) {
            this.type = type;
            this.paint = paint;
            this.startX = x;
            this.startY = y;
            this.endX = x;
            this.endY = y;
            if (type == ShapeType.FREE_HAND) {
                this.path = new Path();
            }
        }

        void draw(Canvas canvas) {
            switch (type) {
                case FREE_HAND:
                    canvas.drawPath(path, paint);
                    break;
                case RECTANGLE:
                    float left = Math.min(startX, endX);
                    float top = Math.min(startY, endY);
                    float right = Math.max(startX, endX);
                    float bottom = Math.max(startY, endY);
                    canvas.drawRect(left, top, right, bottom, paint);
                    break;
                case CIRCLE:
                    float radius = (float) Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));
                    canvas.drawCircle(startX, startY, radius, paint);
                    break;
                case LINE:
                    canvas.drawLine(startX, startY, endX, endY, paint);
                    break;
            }
        }
    }
}
